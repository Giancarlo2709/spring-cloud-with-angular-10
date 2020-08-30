import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Student } from '../../models/student';
import { Course } from '../../models/course';
import { Exam } from '../../models/exam';
import { StudentService } from '../../services/student.service';
import { CourseService } from '../../services/course.service';
import { AnswerService } from '../../services/answer.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import { AnwerExamModalComponent } from '../../components/students/anwer-exam-modal.component'
import { Answer } from 'src/app/models/answer';
import Swal from 'sweetalert2';
import { ViewExamModalComponent } from './view-exam-modal.component';

@Component({
  selector: 'app-answer-exam',
  templateUrl: './answer-exam.component.html',
  styleUrls: ['./answer-exam.component.css']
})
export class AnswerExamComponent implements OnInit {

  student: Student;
  course: Course;
  exams: Exam[] = [];

  showColumnsExams = ['id','name', 'subjects', 'questions', 'answer', 'view'];

  dataSource: MatTableDataSource<Exam>;

  // static true, permite usar el paginator en el ngOnInit
  @ViewChild(MatPaginator, { static : true}) paginator : MatPaginator;

  pageSizeOptions = [3, 5, 10, 20, 50];

  constructor(
    private route: ActivatedRoute,
    private studentService: StudentService,
    private courseService: CourseService,
    public dialog: MatDialog, // open modal
    private answerService: AnswerService
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id: number = +params.get('id');
      this.studentService.findById(id).subscribe(s => {
        this.student = s;
        this.courseService.findCourseByStudentId(this.student)
          .subscribe(course => {
            this.course = course;
            this.exams = (course && course.exams) ? course.exams : [];
            this.dataSource = new MatTableDataSource<Exam>(this.exams);
            this.initPaginator();
          });
      });
    })
    
  }

  private initPaginator() : void {
    this.dataSource = new MatTableDataSource<Exam>(this.exams);
    this.dataSource.paginator = this.paginator;
    this.paginator._intl.itemsPerPageLabel = 'Registros por página';
  }

  asnwerExam(exam: Exam) : void {
    const modalRef = this.dialog.open(AnwerExamModalComponent, { 
      width: '750px',
      data: {
        course: this.course,
        student: this.student,
        exam: exam
      }
     });

     modalRef.afterClosed().subscribe((answersMap: Map<number, Answer>) => {
       if (answersMap) {
        console.log('Modal responder examen ha sido enviado y cerrado');
        console.log(answersMap);
        const answers: Answer[] = Array.from(answersMap.values()); // convertir map to array
        this.answerService.saveAll(answers).subscribe(as => {
          exam.answered = true;
          Swal.fire(
            'Enviadas:',
            'Preguntas enviadas con éxito',
            'success'
          );
          console.log(as);  
        });

       }
     });
  }

  viewExam(exam: Exam) : void {
    console.log(exam);    
    this.answerService.findAnswersByStudentIdAndExamId(this.student, exam)
      .subscribe(an => {
        const modalRef = this.dialog.open(ViewExamModalComponent, {
          width: '750px',
          data: {
            course: this.course,
            exam: exam,
            answers: an
          }
        });

        modalRef.afterClosed().subscribe(() => {
          console.log('Modal ver examen cerrado');
        });

      });
  }

}
