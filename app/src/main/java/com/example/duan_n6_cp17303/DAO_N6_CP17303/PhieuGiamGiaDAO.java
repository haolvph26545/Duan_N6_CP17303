package com.example.duan_n6_cp17303.DAO_N6_CP17303;

import android.util.Log;

import com.example.duan_n6_cp17303.DBHelper_N6_CP17303.MyDBHelper;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.BinhLuanDTO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.PhieuGiamGiaDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PhieuGiamGiaDAO {
    Connection objConn;
    public PhieuGiamGiaDAO(){
        // hàm khởi tạo để mở kết nối
        MyDBHelper db = new MyDBHelper();
        objConn = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
    }

    public List<PhieuGiamGiaDTO> getAll(){
        List<PhieuGiamGiaDTO> listCat = new ArrayList<PhieuGiamGiaDTO>();

        try {
            if (this.objConn != null) {

                String sqlQuery = "SELECT * FROM PHIEUGIAMGIA ";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    PhieuGiamGiaDTO phieuGiamGiaDTO = new PhieuGiamGiaDTO();
                    phieuGiamGiaDTO.setIdphieugiamgia(resultSet.getInt("ID"));
                    phieuGiamGiaDTO.setMagiamgia(resultSet.getString("MAGIAMGIA"));
                    phieuGiamGiaDTO.setPhantram(resultSet.getString("PHANTRAM"));



                    listCat.add(phieuGiamGiaDTO);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  listCat;
    }
    public void insertRow (PhieuGiamGiaDTO phieuGiamGiaDTO){

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String insertSQL = "INSERT INTO PHIEUGIAMGIA(MAGIAMGIA,PHANTRAM) VALUES (N'" + phieuGiamGiaDTO.getMagiamgia() +"','"+phieuGiamGiaDTO.getPhantram()+"')";

                String generatedColumns[] = { "ID" };

                PreparedStatement stmtInsert = this.objConn.prepareStatement(insertSQL, generatedColumns);
                stmtInsert.execute();

                Log.d("zzzzz", "insertRow: finish insert");
                // lấy ra ID cột tự động tăng
                ResultSet rs = stmtInsert.getGeneratedKeys();
                if (rs.next()) {
                    long id = rs.getLong(1);
                    Log.d("zzzz", "insertRow: ID = " + id);
                }

            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "insertRow: Có lỗi thêm dữ liệu " );
            e.printStackTrace();
        }
    }

    public void updateRow(PhieuGiamGiaDTO phieuGiamGiaDTO){

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "UPDATE PHIEUGIAMGIA SET name= " + phieuGiamGiaDTO.getMagiamgia()+"','"+ phieuGiamGiaDTO.getPhantram() + "'WHERE id = " + phieuGiamGiaDTO.getPhantram();


                PreparedStatement stmt = this.objConn.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "updateRow: finish Update");


            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "updateRow: Có lỗi sửa dữ liệu " );
            e.printStackTrace();
        }
    }
}
