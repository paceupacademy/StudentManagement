package com.student.app.service;

import com.student.app.model.*;
import com.student.app.repository.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import com.student.app.model.StudentAcademic;
import com.student.app.model.StudentAttendance;
import com.student.app.model.StudentPersonal;
import com.student.app.model.StudentSports;


import java.io.InputStream;
import java.util.*;

@Service
public class StudentServiceImplementation implements StudentService {

	@Autowired
	private StudentPersonalRepository personalRepo;
	@Autowired
	private StudentAcademicRepository academicRepo;
	@Autowired
	private StudentAttendanceRepository attendanceRepo;
	@Autowired
	private StudentSportsRepository sportsRepo;
	@Autowired
	private StudentJoinRepository joinRepo;

	// In-memory map for student cache
	private Map<Integer, StudentPersonal> studentCache = new HashMap<>();

	@Override
	public void saveStudentsFromExcel(MultipartFile file) {
		try (InputStream is = file.getInputStream(); //get input stream from uploaded excel file
				Workbook workbook = new XSSFWorkbook(is)) { //XSSFWorkbook is used to read .xlsx format 

			// Personal Sheet
			Sheet personalSheet = workbook.getSheet("Personal"); //Gets Personal Sheet
			List<StudentPersonal> personalList = new ArrayList<>(); //Initialize a list to store StudentPersonal objects
			Iterator<Row> iterator = personalSheet.iterator();
			iterator.next(); // Skip first row i.e header
			while (iterator.hasNext()) { //looping over each row
				Row row = iterator.next();
				StudentPersonal s = new StudentPersonal(); //object for each row
				s.setStudentId((int) row.getCell(0).getNumericCellValue()); //StudentID
				s.setFirstName(row.getCell(1).getStringCellValue()); //FirstName
				s.setLastName(row.getCell(2).getStringCellValue()); //Lastname
				s.setDob(row.getCell(3).getStringCellValue()); //Dateof Birth
				s.setContactNumber(row.getCell(4).getStringCellValue()); //ContactNumber
				personalList.add(s);
			}
			personalRepo.saveAll(personalList); //saves all Student PErsonal Data in DB in one shot

			// Academic Sheet
			Sheet academicSheet = workbook.getSheet("Academic");
			List<StudentAcademic> academicList = new ArrayList<>();
			iterator = academicSheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {
				Row row = iterator.next();
				StudentAcademic s = new StudentAcademic();
				s.setStudentId((int) row.getCell(0).getNumericCellValue());
				s.setDepartment(row.getCell(1).getStringCellValue());
				s.setAverageMarks(row.getCell(2).getNumericCellValue());
				academicList.add(s);
			}
			academicRepo.saveAll(academicList);

			// Attendance Sheet
			Sheet attendanceSheet = workbook.getSheet("Attendance");
			List<StudentAttendance> attendanceList = new ArrayList<>();
			iterator = attendanceSheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {
				Row row = iterator.next();
				StudentAttendance s = new StudentAttendance();
				s.setStudentId((int) row.getCell(0).getNumericCellValue());
				s.setAttendedClasses((int) row.getCell(1).getNumericCellValue());
				s.setTotalClasses((int) row.getCell(2).getNumericCellValue());
				attendanceList.add(s);
			}
			attendanceRepo.saveAll(attendanceList);

			// Sports Sheet
			Sheet sportsSheet = workbook.getSheet("Sports");
			List<StudentSports> sportsList = new ArrayList<>();
			iterator = sportsSheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {
				Row row = iterator.next();
				StudentSports s = new StudentSports();
				s.setStudentId((int) row.getCell(0).getNumericCellValue());
				s.setSportName(row.getCell(1).getStringCellValue());
				s.setLevel(row.getCell(2).getStringCellValue());
				s.setAchievements(row.getCell(3).getStringCellValue());
				sportsList.add(s);
			}
			sportsRepo.saveAll(sportsList);

		} catch (Exception e) {
			throw new RuntimeException("Failed to parse and save Excel data: " + e.getMessage(), e);
		}
	}


	@Override
	public StudentPersonal saveStudentPersonal(StudentPersonal personal) {
		studentCache.put(personal.getStudentId(), personal);
		return personalRepo.save(personal);
	}

	@Override
	public StudentAcademic saveStudentAcademic(StudentAcademic academic) {
		return academicRepo.save(academic);
	}

	@Override
	public StudentAttendance saveStudentAttendance(StudentAttendance attendance) {
		return attendanceRepo.save(attendance);
	}

	@Override
	public StudentSports saveStudentSports(StudentSports sports){
		return sportsRepo.save(sports);
	}

	@Override
	public List<StudentPersonal> getAllStudentPersonal() {
		return personalRepo.findAll();
	}

	@Override
	public StudentPersonal getStudentPersonalById(int id) {
		return personalRepo.findById(id).orElse(null);
	}
	
	@Override
	public StudentAcademic getStudentAcademicById(int id) {
        return academicRepo.findById(id).orElse(null);
    }
	
	@Override
	public StudentAttendance getStudentAttendanceById(int id) {
        return attendanceRepo.findById(id).orElse(null);
    }
	
	@Override
    public StudentSports getStudentSportsById(int id) {
        return sportsRepo.findById(id).orElse(null);
    }



	@Override
	public List<StudentFullInfoDTO> getFullInfo() {
		List<Object[]> rows = joinRepo.fetchFullStudentInfoNative();

		List<StudentFullInfoDTO> result = new ArrayList<>();
		for (Object[] row : rows) {
			StudentFullInfoDTO dto = new StudentFullInfoDTO(
					(Integer) row[0],              // student_id
					(String) row[1],               // first_name
					(String) row[2],               // last_name
					(String) row[3],               // department
					(Double) row[4],               // average_marks
					(Integer) row[5],              // attended_classes
					(Integer) row[6],              // total_classes
					(String) row[7],               // sport_name
					(String) row[8]                // level
					);
			result.add(dto);
		}

		return result;
	}


	@Override
    public byte[] generateStudentReportPDF(int studentId) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            StudentPersonal personal = getStudentPersonalById(studentId);
            StudentAcademic academic = getStudentAcademicById(studentId);
            StudentAttendance attendance = getStudentAttendanceById(studentId);
            StudentSports sports = getStudentSportsById(studentId);

            Document document = new Document();
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            document.add(new Paragraph("Student Report"));
            document.add(new Paragraph("---------------------------------------------------"));

            if (personal != null) {
                document.add(new Paragraph("Personal Info:"));
                document.add(new Paragraph("ID: " + personal.getStudentId()));
                document.add(new Paragraph("Name: " + personal.getFirstName() + " " + personal.getLastName()));
                document.add(new Paragraph("DOB: " + personal.getDob()));
                document.add(new Paragraph("Contact: " + personal.getContactNumber()));
                document.add(new Paragraph(" "));
            }

            if (academic != null) {
                document.add(new Paragraph("Academic Info:"));
                document.add(new Paragraph("Department: " + academic.getDepartment()));
                document.add(new Paragraph("Year: " + academic.getAverageMarks()));
                document.add(new Paragraph(" "));
            }

            if (attendance != null) {
            	document.add(new Paragraph("Attendance Info:"));
                document.add(new Paragraph("Attended Classes: " + attendance.getAttendedClasses()));
                document.add(new Paragraph("Total Classes: " + attendance.getTotalClasses()));
                document.add(new Paragraph(" "));
            }

            if (sports != null) {
                document.add(new Paragraph("Sports Info:"));
                document.add(new Paragraph("Sport: " + sports.getSportName()));
                document.add(new Paragraph("Level: " + sports.getLevel()));
                document.add(new Paragraph("Achievements: " + sports.getAchievements()));
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return byteArrayOutputStream.toByteArray();
    }
}
