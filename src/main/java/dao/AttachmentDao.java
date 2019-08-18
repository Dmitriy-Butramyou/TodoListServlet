package dao;

import model.Attachment;

public interface AttachmentDao {

    Attachment getOne(Long taskId);
    Attachment save(Attachment attachment);
    void  remove(Long attachmentId);
}
