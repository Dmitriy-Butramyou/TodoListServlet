package dao;

import model.Attachment;

import java.util.List;

public interface AttachmentDao {

    List<Attachment> getAll();
    Attachment getOne(Long taskId);
    Attachment save(Attachment attachment);
    void  remove(Long attachmentId);
}
