package com.abc.bookstore.client.notice.service;

import com.abc.bookstore.client.notice.dao.NoticeDao;
import com.abc.bookstore.commons.beans.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Override
    public Notice findNoticeByTime() {

        return noticeDao.findNoticeByTime();
    }
}
