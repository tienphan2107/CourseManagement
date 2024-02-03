/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.sql.Connection;

import Database.JDBCUtil;

/**
 *
 * @author DELL
 */
public class TestJDBCUtil {
    public static void main(String[] args) {
		Connection connection = JDBCUtil.getConnection();
                
		JDBCUtil.printInfo(connection);
		
		JDBCUtil.closeConnection(connection);
		
	}
}
