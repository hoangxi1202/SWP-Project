/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ResidentDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.Utils;

/**
 *
 * @author Nhat Linh
 */
public class ResidentDAO {

    private static final String SEARCH_BY_NAME = "SELECT residentId, ownerId, fullName, dob, sex, job, phone FROM "
            + "Residents WHERE fullName LIKE ? and status = 1";
    private static final String SEARCH_BY_NAME_OWN = "SELECT Residents.residentId, Residents.ownerId, Residents.fullName, "
            + "Residents.dob, Residents.sex, Residents.job, Residents.phone FROM "
            + "Residents, Owners WHERE Residents.ownerId = Owners.ownerId"
            + " AND Owners.userId LIKE ? AND Residents.status = 1";
    private static final String SEARCH_BY_OWN = "SELECT residentId FROM "
            + "Residents WHERE ownerId LIKE ?";
    private static final String GET_INDEX_REQUEST = "SELECT requestId from Requests";
    private static final String ADD_RESIDENT = "INSERT INTO Residents VALUES (?, ?, ?, ?, ?, ?, 0, ?, ?)";
    private static final String UPDATE_RESIDENT = "UPDATE Residents SET requestId = ? WHERE residentId = ?";
    private static final String INSERT_REQUEST = "INSERT INTO Requests VALUES (?, ?, ?, ?)";
    private static final String VIEW_REQUEST = "SELECT Requests.requestId, Residents.ownerId, Residents.residentId, Residents.fullName, Residents.sex, "
            + " Residents.dob, Residents.phone, Residents.status, Residents.job"
            + " FROM Requests, Residents "
            + " WHERE Requests.requestId = Residents.requestId "
            + " AND Requests.status = 0 AND action = ?";

    public List<ResidentDTO> getListRequestRes(String action) throws SQLException {
        List<ResidentDTO> listResident = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(VIEW_REQUEST);
                ptm.setString(1, action);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String residentId = rs.getString("residentId");
                    String ownerId = rs.getString("ownerId");
                    String name = rs.getString("name");
                    String dob = rs.getString("dob");
                    boolean gender = Utils.getBoolean(rs.getString("gender"));
                    String job = rs.getString("job");
                    String phone = rs.getString("phone");
                    String req = rs.getString("requestId");
                    String status = rs.getString("status");
                    boolean check = false;
                    if ("1".equals(status)) {
                        check = true;
                    }
                    listResident.add(new ResidentDTO(residentId, ownerId, name, dob, gender, job, phone, check, req));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listResident;

    }

    public boolean updateResident(String requestId, String residentId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Utils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(UPDATE_RESIDENT);
                stm.setString(1, requestId);
                stm.setString(2, residentId);
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return check;

    }

    public List<ResidentDTO> getListResidentV2(String input) throws SQLException {
        List<ResidentDTO> listResident = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_BY_NAME_OWN);
                ptm.setString(1, input + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String residentId = rs.getString("residentId");
                    String ownerId = rs.getString("ownerId");
                    String name = rs.getString("name");
                    String dob = rs.getString("dob");
                    boolean gender = Utils.getBoolean(rs.getString("gender"));
                    String job = rs.getString("job");
                    String phone = rs.getString("phone");
                    String req = "";
                    listResident.add(new ResidentDTO(residentId, ownerId, name, dob, gender, job, phone, true, req));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listResident;
    }

    public List<ResidentDTO> getListResident(String search) throws SQLException {
        List<ResidentDTO> listResident = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_BY_NAME);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String residentId = rs.getString("residentId");
                    String ownerId = rs.getString("ownerId");
                    String name = rs.getString("name");
                    String dob = rs.getString("dob");
                    boolean gender = Utils.getBoolean(rs.getString("gender"));
                    String job = rs.getString("job");
                    String phone = rs.getString("phone");
                    String req = "";
                    listResident.add(new ResidentDTO(residentId, ownerId, name, dob, gender, job, phone, true, req));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listResident;
    }

    public int getIndexResident(String search) throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_BY_OWN);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    count++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return count;
    }

    public int getIndexRequest() throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_INDEX_REQUEST);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    count++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return count;
    }

    public boolean addResident(ResidentDTO res) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Utils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(ADD_RESIDENT);
                stm.setString(1, res.getResidentId());
                stm.setString(2, res.getName());
                stm.setString(3, res.getDob());
                if (res.isGender() == true) {
                    stm.setString(4, "male");
                } else {
                    stm.setString(4, "female");
                }
                stm.setString(5, res.getJob());
                stm.setString(6, res.getPhone());
                stm.setString(7, res.getResquestId());
                stm.setString(8, res.getOwnerId());
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return check;
    }

    public boolean insertRequest(String req, String ownerId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Utils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(INSERT_REQUEST);
                stm.setString(1, req);
                stm.setString(2, "add");
                stm.setString(3, "0");
                stm.setString(4, ownerId);
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return check;
    }

    public boolean insertRequestV2(String req, String ownerId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Utils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(INSERT_REQUEST);
                stm.setString(1, req);
                stm.setString(2, "delete");
                stm.setString(3, "0");
                stm.setString(4, ownerId);
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return check;
    }
}
