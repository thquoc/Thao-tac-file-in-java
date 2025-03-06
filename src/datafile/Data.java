package datafile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.SinhVien;

public class Data {
	String filePath = "sinhvien.txt";
	
	public Data() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean addSV(SinhVien sv) {
		String ndGhi = sv.getMaSV() + ";" + sv.getTenSV() + ";" + sv.getDiaChi();

		try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath, true))){
			bw.write(ndGhi);
			bw.newLine();
			return true;
			
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return false;
		
	}
	
	public boolean editSV(SinhVien sv) {
		//String[][] data = null;
		List<String> dsghi = new ArrayList<String>();
		boolean isUpdated = false;
		try(BufferedReader br = new BufferedReader(new FileReader(this.filePath))){
			String sv1;
			while((sv1 = br.readLine())!=null) {
				String[] sinhvien = sv1.split(";");
				if(sinhvien[0].equals(sv.getMaSV())) {
					sinhvien[1] = sv.getTenSV();
					sinhvien[2] = sv.getDiaChi();
					isUpdated = true;
				}
				dsghi.add(String.join(";", sinhvien));
				
			}

			//int demLine = 0;
			/*String lineData;
			while((lineData = br.readLine()) != null) {
				//demLine++;
				String[] thongtinSV = lineData.split(";");
				if(thongtinSV[0].equals(sv.getMaSV())) {
					thongtinSV[1] = sv.getTenSV();
					thongtinSV[2] = sv.getDiaChi();
					isUpdated = true;
				}
				
				String capnhatthongtinSV = thongtinSV[0]+";"+thongtinSV[1]+";"+thongtinSV[2];
				try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath))){
					bw.write(capnhatthongtinSV);
					bw.newLine();
			
				}
			}*/

			
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath))){
			for(String strSv : dsghi) {
				bw.write(strSv);
				bw.newLine();
			}
			
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return isUpdated;
		
	}
	
	public boolean deleteSV(SinhVien sv) {
		boolean isUpdated  = false;
		List<String> dsghi = new ArrayList<String>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(this.filePath))){
			String line;
			while((line = br.readLine()) != null) {
				String[] thongtinSV = line.split(";");
				if(thongtinSV[0].equals(sv.getMaSV())) {
					isUpdated = true;
					continue;
				}
				
				String newdata = thongtinSV[0]+";"+thongtinSV[1]+";"+thongtinSV[2];
				dsghi.add(newdata);
				
			}
			
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath))) {
			for(String strSV : dsghi) {
				bw.write(strSV);
				bw.newLine();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return isUpdated;
		
	}
	
	public SinhVien searchSV(String maSV) {
		try(BufferedReader br = new BufferedReader(new FileReader(this.filePath))){
			String line;
			while((line = br.readLine()) != null) {
				String[] thongtinSV = line.split(";");
				if(thongtinSV[0].equals(maSV)){
					return new SinhVien(
						
							thongtinSV[1],
							maSV, 
							thongtinSV[2]);
		
				}
			}
			
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		
	}
	
	public List<SinhVien> getAllSV(){
		List<SinhVien> students = new ArrayList<SinhVien>();
		//SinhVien stu;
		try(BufferedReader br = new BufferedReader(new FileReader(this.filePath))){
			String sv;
			while((sv = br.readLine()) != null) {
				String[] thongtinSv = sv.split(";");
				SinhVien stu = new SinhVien(
						thongtinSv[1],
						thongtinSv[0],
						thongtinSv[2]
						);
				
				students.add(stu);
				
			}
			
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return students;
	}

}
