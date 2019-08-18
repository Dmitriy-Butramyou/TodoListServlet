package dao.impl;

import dao.AttachmentDao;
import jdbc.connector.MySqlConnector;
import jdbc.query.MySqlQuery;
import model.Attachment;
import util.CloseConnection;

import java.sql.*;

public class AttachmentDaoImpl implements AttachmentDao {

    private Connection connection = MySqlConnector.getInstance().getConnection();

    @Override
    public Attachment getOne(Long taskId) {
        String query = MySqlQuery.getInstance().getQuery("attachmentGetOne");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, taskId);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return Attachment
                        .builder()
                        .id(resultSet.getLong(1))
                        .originalName(resultSet.getString(2))
                        .generatedName(resultSet.getString(3))
                        .generatedPath(resultSet.getString(4))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            CloseConnection.close(preparedStatement, resultSet);
        }

        return null;
    }

    @Override
    public Attachment save(Attachment attachment) {
        PreparedStatement preparedStatement = null;
        String query = attachment.getId() != null ?
                MySqlQuery.getInstance().getQuery("attachmentUpdate") :
                MySqlQuery.getInstance().getQuery("attachmentInsert");
        try {
            preparedStatement = attachment.getId() != null ?
                    connection.prepareStatement(query) :
                    connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, attachment.getTaskId());
            preparedStatement.setString(2, attachment.getOriginalName());
            preparedStatement.setString(3, attachment.getGeneratedName());
            preparedStatement.setString(4, attachment.getGeneratedPath());
            if(attachment.getId() != null) {
                preparedStatement.setLong(5, attachment.getId());
                return preparedStatement.executeUpdate() > 0 ? attachment : null;
            } else {
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()) {
                    attachment.setId(resultSet.getLong(1));
                    resultSet.close();
                    return attachment;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            CloseConnection.close(preparedStatement, null);
        }
        return null;
    }

    @Override
    public void remove(Long attachmentId) {
        String query = MySqlQuery.getInstance().getQuery("attachmentDelete");
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, attachmentId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            CloseConnection.close(preparedStatement, null);
        }
    }
}
