# 📄 Student Management System - REST API Documentation

## 🔗 Base URL

```
http://localhost:8080/api
```

---

## 🔐 Authentication

If security is enabled, use the following credentials:

| Parameter | Value      |
| --------- | ---------- |
| Username  | aishwarya  |
| Password  | password   |
| Auth Type | Basic Auth |

Enable "Authorization" tab in Postman or use `-u` flag in `curl`.

---

## 🗋 REST API Endpoints

### ✉️ 1. Upload Excel File (Student Data)

```
POST /api/students/upload
```

#### 🔹 Description:

Uploads Excel containing student personal, academic, attendance, and sports info.

#### 🔹 Headers:

```
Content-Type: multipart/form-data
```

#### 🔹 Body (Postman -> form-data):

| Key  | Value                                  |
| ---- | -------------------------------------- |
| file | StudentData\_Upload\_100\_Records.xlsx |

#### 🔹 Sample Curl Command:

```bash
curl -X POST http://localhost:8080/api/students/upload \
     -H "Content-Type: multipart/form-data" \
     -F "file=@StudentData_Upload_100_Records.xlsx" \
     -u aishwarya:password
```

> ⚡ **Run this command in the root directory where the Excel file is located**, using terminal or Git Bash.

---

### 📅 2. Get All Student Personal Info

```
GET /api/students/personal
```

#### Sample Response:

```json
[
  {
    "studentId": 19,
    "firstName": "Ashley",
    "lastName": "Gordon",
    "dob": "2001-06-05",
    "contactNumber": "kyle69@yahoo.com"
  },
  ...
]
```

---

### 💼 3. Get All Academic Info

```
GET /api/students/academic
```

---

### ⏳ 4. Get All Attendance Info

```
GET /api/students/attendance
```

---

### 🏅 5. Get All Sports Info

```
GET /api/students/sports
```

---

### 🔖 6. Download Student Report as PDF

```
GET /api/students/report/{studentId}
```

#### Example:

```
GET /api/students/report/19
```

#### Response:

* `Content-Type: application/pdf`
* File download of full student report

---

### 🔢 7. Test Endpoint

```
GET /api/test
```

Simple response: `"Service is up"`

---

## 📊 Tools for Testing

| Tool       | Purpose                   |
| ---------- | ------------------------- |
| Postman    | API testing (recommended) |
| Curl       | CLI-based testing         |
| SoapUI     | For SOAP + REST           |
| Swagger UI | Optional integration      |

---

## 🚫 Troubleshooting

| Issue              | Solution                                    |
| ------------------ | ------------------------------------------- |
| 401 Unauthorized   | Enable Basic Auth in Postman/curl           |
| 415 Media Type     | Use `multipart/form-data` for upload        |
| Blank PDF          | Check if all student data tables are filled |
| File not uploading | Verify correct form-data key as `file`      |

---

## ▶️ How to Run

```bash
mvn spring-boot:run
```

Ensure:

* MySQL is running
* `StudentData_Upload_100_Records.xlsx` is ready
* Tables: `student_personal`, `student_academic`, `student_attendance`, `student_sports` are created

---




# Student Management SOAP Service


This project provides a SOAP web service to fetch student report data and return a Base64-encoded PDF.

---

## 📌 WSDL Endpoint

http://localhost:8080/ws/student-report.wsdl
---

🔐 Authentication (if enabled)
Type: Basic Auth

Username: admin

Password: admin

Pre-emptive Auth: Yes

---
## 📥 SOAP Request Format

```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:rep="http://student.com/report">
   <soapenv:Header/>
   <soapenv:Body>
      <rep:StudentReportRequest>
         <rep:studentId>19</rep:studentId>
      </rep:StudentReportRequest>
   </soapenv:Body>
</soapenv:Envelope>
```
---
## 📥 SOAP Response Format
```
<StudentReportResponse>
    <pdfBase64>Base64-encoded PDF data...</pdfBase64>
</StudentReportResponse>
```

---
📄 Convert Base64 to PDF
Option 1: Online
Use https://www.base64decode.net/base64-to-pdf

Copy pdfBase64 value (without XML tags).

Paste it.

Click “Convert” → “Download PDF”.

---
🐞 Troubleshooting
Issue	Solution
401 Unauthorized - Add Basic Auth in SoapUI
Empty response - Verify studentId exists
PDF is corrupt - Make sure Base64 is fully copied without newline/whitespace

