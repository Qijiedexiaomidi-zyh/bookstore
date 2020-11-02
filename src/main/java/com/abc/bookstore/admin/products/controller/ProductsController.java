package com.abc.bookstore.admin.products.controller;

import com.abc.bookstore.admin.products.service.ProductsService;
import com.abc.bookstore.commons.beans.Product;
import com.abc.bookstore.commons.beans.ProductVo;
import com.abc.bookstore.utils.IdUtils;
import com.alipay.api.internal.util.codec.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    /**
     * 商品查询
     */
    @RequestMapping("/listProduct")
    public String listProduct(HttpSession session){

        List<Product> products = productsService.listProduct();
        session.setAttribute("products",products);
        return "/admin/products/list.jsp";
    }

    /**
     * 商品条件查询
     */
    @RequestMapping("/findProductByManyCondition")
    public String findProductByManyCondition(Product product,Double maxprice,Double minprice,HttpSession session){

        List<Product> products = productsService.findProductByManyCondition(product,maxprice,minprice);
        session.setAttribute("product",product);
        session.setAttribute("maxprice",maxprice);
        session.setAttribute("minprice",minprice);
        session.setAttribute("products",products);
        return "/admin/products/list.jsp";
    }

    /**
     * 商品添加
     */
    @RequestMapping("/addProduct")
    public String addProduct(Product product, MultipartFile upload,HttpSession session) throws IOException {

        //获取该文件的发布路径，但是项目一旦重启，之前上传过的图片都会消失，因为它是上传到了webapp下的target下面，项目一重启，target会重新生成一份，所以还要再上传到项目路径一份
        String path = session.getServletContext().getRealPath("/productImg");
        System.out.println("文件上传的发布路径："+path);
        //获取该文件的项目本身路径
        String path1 = "D:\\idea\\Spring MVC\\bookstore\\src\\main\\webapp\\productImg";
        System.out.println("文件上传的项目路径："+path1);
        File file = new File(path);

        //判断该文件是否存在
        if (!file.exists()){
            file.mkdirs();
        }

        String filename = IdUtils.getUUID() + "-" + upload.getOriginalFilename();
        upload.transferTo(new File(path,filename));
        //文件拷贝
        FileUtils.copyFile(new File(path,filename),new File(path1,filename));

        product.setId(IdUtils.getUUID());
        product.setImgurl("/productImg/"+filename);
        productsService.addProduct(product);

        return "redirect:/admin/products/listProduct";
    }

    /**
     * 商品细节查询
     */
    @RequestMapping("/findProductById")
    public String findProductById(String id,HttpSession session){

        Product product = productsService.findProductById(id);
        session.setAttribute("p",product);
        return "/admin/products/edit.jsp";
    }

    /**
     * 修改商品上架信息
     */
    @RequestMapping("/editProduct")
    public String editProduct(Product product,MultipartFile upload,HttpSession session) throws IOException {

        //是否上传了新的图片
        if (!upload.isEmpty()){
            //查看原始图片的信息
            Product target = productsService.findProductById(product.getId());
            //获取原始图片文件
            File targetFile = new File(session.getServletContext().getRealPath("/")+target.getImgurl());
            //删除原始图片文件
            if (targetFile.exists()){
                targetFile.delete();
            }

            //上传新的图片
            String path = session.getServletContext().getRealPath("/productImg");
            String filename = IdUtils.getUUID()+"-"+upload.getOriginalFilename();
            upload.transferTo(new File(path,filename));
            //如果上传了新的图片，给product的imgurl赋值
            product.setImgurl("/productImg/"+filename);
        }

        //修改其对应的数据库信息
        productsService.editProduct(product);
        session.setAttribute("product",product);
        return "redirect:/admin/products/listProduct";
    }

    /**
     * 删除商品
     */
    @RequestMapping("/deleteProduct")
    public String deleteProduct(String id,HttpSession session){

        //删除文件夹里面的图片文件
        Product target = productsService.findProductById(id);
        File targetFile = new File(session.getServletContext().getRealPath("/")+target.getImgurl());
        if (targetFile.exists()){
            targetFile.delete();
        }

        //删除数据库中的内容
        productsService.deleteProduct(id);
        return "redirect:/admin/products/listProduct";
    }

    /**
     * 下载销售榜单信息
     */
    @RequestMapping("/download")
    public void download(String year, String month, HttpServletResponse response,HttpServletRequest request) throws IOException {
        List<ProductVo> productVos = productsService.findSalenum(year,month);
        for (ProductVo productVo : productVos) {
            System.out.println(productVo);
        }

        String filename = year + "年" + month +"月销售榜单";
        String sheetName = month +"月销售榜单";
        String titleName = year + "年" + month +"月销售榜单";
        String[] columnName = {"商品名称","销售数量"};

        String[][] data = new String[productVos.size()][2];
        for (int i=0;i<productVos.size();i++){
            data[i][0] = productVos.get(i).getName();
            data[i][1] = productVos.get(i).getSalenum();
        }
        //创建Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建Excel中的sheet表
        HSSFSheet sheet = wb.createSheet(sheetName);
        //创建sheet的第一行
        HSSFRow row1 = sheet.createRow(0);
        //创建sheet的第一行的第一个单元格
        HSSFCell cell = row1.createCell(0);
        //合并第一行的两个单元格
        //起始行，终止行，起始列，终止列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,1));
        //给第一行赋值
        cell.setCellValue(titleName);

        //创建第二行，并赋值
        HSSFRow row = sheet.createRow(1);
        for (int i=0;i<2;i++){
            row.createCell(i).setCellValue(columnName[i]);
        }

        //创建第二行以后的数据行，并赋值
        for (int i=0;i<data.length;i++){
            row = sheet.createRow(i+2);
            for (int j=0;j<2;j++){
                row.createCell(j).setCellValue(data[i][j]);
            }
        }

        //返回响应的数据，形成文件
        String fileName = filename + ".xls";
        //设置响应数据的类型
        response.setContentType("application/ms-excel;charset=UTF-8");
        //设置响应头的信息
        response.setHeader("content-Disposition","attachment;filename="+encodeChineseDownloadFileName(request,fileName));
        //以文件流的形式响应回去
        OutputStream out = response.getOutputStream();
        wb.write(out);
    }

    /**
     * 解决下载文件乱码的问题
     */
    public static String encodeChineseDownloadFileName(HttpServletRequest request, String pFileName)
            throws UnsupportedEncodingException {
        String filename = null;
        String agent = request.getHeader("USER-AGENT");
        if (null != agent) {
            if (-1 != agent.indexOf("Firefox")) {//Firefox
                filename =
                        "=?UTF-8?B?" + (new String(Base64.encodeBase64(pFileName.getBytes("UTF-8")))) + "?=";
            } else if (-1 != agent.indexOf("Chrome")) {//Chrome
                filename = new String(pFileName.getBytes(), "ISO8859-1");
            } else {//IE7+
                filename = URLEncoder.encode(pFileName, "UTF-8");
                filename = StringUtils.replace(filename, "+", "%20");//替换空格
            }
        } else {
            filename = pFileName;
        }

        return filename;
    }
}
