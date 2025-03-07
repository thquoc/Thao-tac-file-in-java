
package UI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import datafile.Data;
import models.SinhVien;

import java.awt.*;

import java.util.List;

public class SinhVienUI {
    public static void main(String[] args) {
        new StudentManagementGUI();
    }
}

class StudentManagementGUI extends JFrame {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private JTextField txtId, txtName, txtAddress;
    private JTable table;
    private DefaultTableModel tableModel;

    public StudentManagementGUI() {
        setTitle("StuManager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tiêu đề
        JLabel lblTitle = new JLabel("STUDENT MANAGEMENT", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(Color.BLUE);
        add(lblTitle, BorderLayout.NORTH);

        // Panel nhập dữ liệu
        JPanel panelInput = new JPanel(new GridLayout(4, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelInput.add(new JLabel("StuID:"));
        txtId = new JTextField();
        panelInput.add(txtId);

        panelInput.add(new JLabel("Name:"));
        txtName = new JTextField();
        panelInput.add(txtName);

        panelInput.add(new JLabel("Address:"));
        txtAddress = new JTextField();
        panelInput.add(txtAddress);

        add(panelInput, BorderLayout.WEST);

        // Bảng dữ liệu
        String[] columnNames = {"StuID", "Name", "Address"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Thêm dữ liệu mẫu
        loadTable();

        // Panel nút bấm
        JPanel panelButtons = new JPanel(new GridLayout(3, 2, 5, 5));
        panelButtons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnAdd = new JButton("Add");
        JButton btnEdit = new JButton("Edit");
        JButton btnClear = new JButton("Clear");
        JButton btnDelete = new JButton("Delete");
        JButton btnSearch = new JButton("Search");
        JButton btnCancel = new JButton("Cancel");

        panelButtons.add(btnAdd);
        panelButtons.add(btnDelete);
        panelButtons.add(btnEdit);
        panelButtons.add(btnSearch);
        panelButtons.add(btnClear);
        panelButtons.add(btnCancel);

        //add(panelButtons, BorderLayout.SOUTH);
        panelInput.add(panelButtons,BorderLayout.SOUTH);

        setVisible(true);
        
        btnAdd.addActionListener(e -> {
        	String tenSV = txtName.getText().trim();
        	String maSV = txtId.getText().trim();
        	String diachiSV = txtAddress.getText().trim();
        	
        	if(tenSV.isEmpty() | maSV.isEmpty() | diachiSV.isEmpty()) {
        		JOptionPane.showMessageDialog(this, "Vui long nhap thong tin sinh vien (Ma sinh vien, Ho ten, dia chi) cần thay đổi", "loi", JOptionPane.ERROR_MESSAGE);
        		return;
        		
        	}
        	
        	
        	SinhVien sv = new SinhVien(tenSV, maSV, diachiSV);
        	//Data dt = new Data();
        	Data dt = new Data();
        	boolean success = dt.addSV(sv);
        	//String tb = success ? "Them sinh vien thanh cong": "Khong the them sinh vien";
        	//JOptionPane.showConfirmDialog(this, tb, "Thong bao", JOptionPane.ERROR_MESSAGE);
        	if(success) {
        		JOptionPane.showMessageDialog(this, "Them sinh vien thanh cong","Thong bao", JOptionPane.ERROR_MESSAGE);
        		loadTable();
        		
        	}else {
        		JOptionPane.showMessageDialog(this,"Khong the them sinh vien", "Loi", JOptionPane.ERROR_MESSAGE );
        		
        	}
        	 
        });
        
        btnDelete.addActionListener(e -> {
        	int selectedRow = table.getSelectedRow();
        	if(selectedRow == -1) JOptionPane.showMessageDialog(this, "Ban chua chon sinh vien de xoa", "Loi", JOptionPane.ERROR_MESSAGE);
        	
        	String maSV = (String) table.getValueAt(selectedRow, 0);
        	String tenSV = (String) table.getValueAt(selectedRow, 1);
        	String diachiSV = (String) table.getValueAt(selectedRow, 2);
        	
        	SinhVien sv = new SinhVien(tenSV, maSV, diachiSV);
        	Data dt = new Data();
        	boolean success = dt.deleteSV(sv);
        	//String tb = (success)? "Xoa sinh vien thanh cong":"Khong the xoa sinh vien";
        	//JOptionPane.showMessageDialog(this, tb, "thong bao", JOptionPane.ERROR_MESSAGE);
        	if(success) {
        		JOptionPane.showMessageDialog(this, "Xoa sinh vien thanh cong","Thong bao", JOptionPane.ERROR_MESSAGE);
        		loadTable();
        		
        	}else {
        		JOptionPane.showMessageDialog(this,"Khong the xoa sinh vien", "Loi", JOptionPane.ERROR_MESSAGE );
        		
        	}
        });
        btnSearch.addActionListener(e -> {
        	String maSV = txtId.getText().trim();
        	if(maSV.isEmpty()) {
        		JOptionPane.showMessageDialog(this, "Vui long nhap ma sinh vien tim kiem","Lỗi", JOptionPane.ERROR_MESSAGE);
        		return;
        	}
        	Data dt = new Data();
        	SinhVien sv = dt.searchSV(maSV);
        	
        	DefaultTableModel timkiemtable = TableTimkiem();
        	timkiemtable.setRowCount(0);
        	
        	Object[] svtk = {
        			sv.getMaSV(),
        			sv.getTenSV(),
        			sv.getDiaChi()
        			
        	};
        	timkiemtable.addRow(svtk);
        	
        	
        });
        
        btnEdit.addActionListener(e -> {
        	int chondong = table.getSelectedRow();
        	
        	if(chondong == -1) JOptionPane.showMessageDialog(this, "Chon sinh vien can sua", "Loi", JOptionPane.ERROR_MESSAGE);
        	
        	String maSV = (String)table.getValueAt(chondong,0);
        	String name = txtName.getText().trim();
        	String address = txtAddress.getText().trim();
        	if(name.isEmpty() | address.isEmpty()) {
        		JOptionPane.showMessageDialog(this, "Vui long nhap thong tin sinh vien (Ho ten, dia chi) cần thay đổi", "loi", JOptionPane.ERROR_MESSAGE);
        		return;
        	}
        	
        	SinhVien sv = new SinhVien(name, maSV, address);
        	Data dt = new Data();
        	boolean success = dt.editSV(sv);
        	
        	if(success) {
        		JOptionPane.showMessageDialog(this, "Sua sinh vien thanh cong","Thong bao", JOptionPane.ERROR_MESSAGE);
        		loadTable();
        		
        	}else {
        		JOptionPane.showMessageDialog(this,"Khong the sua sinh vien", "Loi", JOptionPane.ERROR_MESSAGE );
        		
        	}
        	
        	
        });
        
        btnClear.addActionListener(e->{
        	clear();
        });
    }
    private DefaultTableModel TableTimkiem() {
    	JFrame timkiemFrame = new JFrame("Bang hien thi ket qua tim kiem");
    	timkiemFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	
    	DefaultTableModel tkmodels = new DefaultTableModel();
    	tkmodels.addColumn("MaSV");
    	tkmodels.addColumn("TenSV");
    	tkmodels.addColumn("DiaChiSV");
    	
    	JTable tktable = new JTable(tkmodels);
    	JScrollPane jp = new JScrollPane(tktable);
    	timkiemFrame.add(jp);
    	timkiemFrame.setSize(500, 300);
    	timkiemFrame.setLocationRelativeTo(null);
    	timkiemFrame.setVisible(true);
    	
    	return tkmodels;
    	
    	
    }
   private void clear() {
	   txtAddress.setText("");
	   txtId.setText("");
	   txtName.setText("");
	   
   }
   
   private void loadTable() {
	   Data dt = new Data();
	   List<SinhVien> stus = dt.getAllSV();
	   tableModel.setRowCount(0);
	   
	   for(SinhVien sv : stus) {
		   Object[] obj = {
				   sv.getMaSV(),
				   sv.getTenSV(),
				   sv.getDiaChi()
		   };
		   tableModel.addRow(obj);
		   
	   }
	   
	   
   }
}
