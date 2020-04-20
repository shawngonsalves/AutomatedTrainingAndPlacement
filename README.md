# AutomatedTrainingAndPlacement


# Introduction
The increasing advantage of automated systems make it necessary for educational infrastructures like colleges to move all manual processes onto a functional online system. The training and placement cells in colleges lack a fully automated system which can manage and process data in an efficient manner. The development of such a system has become necessary since the number of applicants for recruitment is increasing every year and the means of storing the data generated in this process has become primitive.
# Description
Automated Training and Placement System is an android based application that can be accessed throughout the college. This system can be used as an application for the Training and Placement Officer (TPO) of the college to manage the student information with regards to placement as well as conduct tests and store results online. Automated Training and Placement is an application to facilitate students of Fr. Conceicao Rodrigues College of Engineering to register, search and apply for jobs as well as prepare themselves for the recruitment process through a series of aptitude tests and by accessing information about past interviews of students who are already placed. Students will be able to upload their information in the form of a resume by using the template provided in the application. Company representatives can access required information entered by students. The administrator can manage the data generated in the process as well as produce the required list of results. Providing resume templates, informing students about various job opening and their criteria, conducting regular online aptitude tests, storing and retrieving information of students and companies, providing a platform for students to share their experience in interviews and getting reviews of student’s performance during the placement process from the recruiters are some of the major features of the system.
# Problem Formulation
The existing placement system requires that students constantly use their email to remain informed about the placement process and prepare themselves for the same. There is a need of a system that provides proper login credentials. Personal information of students is stored in an unstructured manner in the existing system. Such a system is more error-prone & time consuming. It is difficult to manage such unstructured data in the system. Conducting aptitude tests in classrooms is a chaotic job since each student’s answer sheet cannot be manually evaluated. Large files of records are to be maintained for the same. Students have no preceding knowledge as to what a company interviewer looks for in a student. It is a time consuming activity of managing, updating and shortlisting eligible students for specific company requirements. The number of applicants registering for placements is large and it is difficult to manage data for each individual.
# Motivation
The above mentioned problems can be solved using a web based android application running on a web server. All automation processes can be integrated within the same application. Considering the fact that the number of applicants for recruitment is increasing every year, an Automated System becomes essential to meet the demand. In Training & Placement Automation System, the organization need not worry about innumerable copies of each document, instead the system will enable a greener and less paper intensive approach to placements. This system uses several programming and database management techniques to elucidate the work involved in this process.
# Proposed Solution
This system is mainly designed to improve accessibility and simplicity of the training and placement process. Automated Training and Placement System consists of modules like Student, Administrator (TPO/ TnP Staff) and Company. This system will be used by students to download resume templates and upload their details. Administrator of the system will upload questions based on different categories and the student can choose which test they want to take. The proposed sys-tem can be used to conduct aptitude tests and store the result which is generated, in an organized manner. The system will also provide a forum for the company to post their feedback about the students appearing for interview. The system will analyze this feedback using natural language processing and sentiment analysis and determine fields of improvement for the students. It will provide the facility of maintaining the details of the students in a structured manner
# Scope of the Project
The scope of our system is widely reached as there are many features which can be added in near future to make the application more interactive, convenient and user friendly. Resume templates can be used to upload student’s details in a structured manner so that it can be easily extracted whenever necessary. The admin can conduct regular aptitude tests based on company requirements to prepare the students for different rounds of interviews. The test questions can be modified as per requirements by the admin of the system. Feedback from students who are placed in a company can be collected describing their experiences in personal interviews. Students appearing for the interview in the future can refer to them to prepare better and increase their chances of doing well in the interview. Companies can post reviews and comments about the campus and the students. These reviews can be analyzed using natural language processing and sentiment analysis to determine fields of improvement for students. Sentiment Analysis (sometimes known as opinion mining or emotion AI) refers to the use of natural language processing, text analysis, computational linguistics, and biometrics to systematically identify, extract, quantify, and study affective states

# System Analysis
# 3.1 Functional Requirements
This section provides requirement overview of the system/application. Various functional modules that can be implemented by the system will be –
Description: The below list describes the functional requirements of different modules of the application.
• Registration: Student users must register into the system using their ERP based credentials and personal information. Companies can also access information from the system by registering before the campus drive.
• Login: Admin, students and company representatives can login into the system by entering proper credentials.
• Upload resume: The system will allow students to download sample resume templates, fill them and submit them to the system admin who will then validate it.
• Aptitude tests: Administrator will be able to design various sets of question and the students can select test categories and take these tests. Results will be recorded and displayed in the system.
• Comments and Reviews: Students may share their experiences in interviews and the company can also post comments about the performance of students. These comments and reviews will be analyzed and different results will be generate that will help the students to improve their performance in interviews and maximize their chance of getting recruited.
• Logout: After the session is over, the user will log out.
• Technical Issues: The server is required to always be in a running state and multiple users shall be able to use the system from anywhere, anytime. The system should be tested regularly and minor bug fixes shall be done time to time.
18

Other Functional Requirement: Below lists shows the functional requirements other than the above specified.
• Make necessary changes in the resume and validate it.
• Authentication of users
• Verification of aggregate marks
• Filtering comments and reviews
• Legal or Regulatory Requirement

# 3.2 Non-Functional Requirements
Performance Requirements:
• Uninterrupted connections i.e. it should be in a operable state at all times after installation. Interface should be interactive and easy to operate and the server should be with quick response time.
• Complete compatibility on any smartphone device.
Safety and Security Requirements:
• Account information must be secure.
• System shall maintain its database with secure login and password.
• The database shall use security service enterprise authentication for connections to the database. All users shall have their own user name and password that is the same across all databases.
• The system should not leave any cookies on the user’s phone containing the user’s password. The system’s back-end servers shall only be accessible to authenticated administrators.

Software Quality Attributes:
• Robustness: The system is robust and secure. The system will always validate the account and the details before performing any steps.
• Reliability: The reliability of the overall program depends on the reliability of the separate components.
• Availability: The system should be available at all times, meaning the user can access it , only restricted by the down time of the server on which the system runs.
• Usability: Any person with basic smartphone knowledge should be able to use the system with ease.
• Correctness: The system should always validate the required account and the transactions it performs, so there is no chance of any flaws.
• Security: The system should not leave any cookies on the user’s phone containing the user’s password.

# 3.3 Specific Requirements
# 3.3.1 Hardware Requirements
The System will run over the internet, so the smartphone must have data pack activated from any service provider.
• Memory of 4 GB RAM or more. Intel i3 Processor or higher.
• 8 GB (or more) available hard disk space.
# 3.3.2 Software Requirements
The system works on server so it requires any scripting language like PHP etc. The system also requires database technologies like Firebase,SQLite etc. to store user data.
• Android Development Tool 1.6.1. Android SDK tools 22.0.5.
• Java SE JDK v6.0.
• Windows 7 Operating System or higher. Database technologies: SQLite, Firebase.
