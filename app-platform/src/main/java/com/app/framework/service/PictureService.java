package com.app.framework.service;

import com.app.framework.auth.service.BaseService;
import com.app.framework.dao.PictureDao;
import com.app.framework.model.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureService extends BaseService<Picture> {
    private PictureDao pictureDao;

    @Autowired
    public PictureService(PictureDao pictureDao) {
        super(pictureDao);
        this.pictureDao = pictureDao;
    }

    public void deleteByOwnerIds(Long[] ownerIds) {
        pictureDao.deleteByOwnerIds(ownerIds);
    }
}
