import { Component, OnInit, Inject } from '@angular/core';
import { Course } from 'src/app/models/course';
import { Student } from 'src/app/models/student';
import { Answer } from 'src/app/models/answer';
import { Exam } from 'src/app/models/exam';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Question } from 'src/app/models/question';

@Component({
  selector: 'app-anwer-exam-modal',
  templateUrl: './anwer-exam-modal.component.html',
  styleUrls: ['./anwer-exam-modal.component.css']
})
export class AnwerExamModalComponent implements OnInit {

  course: Course;
  student: Student;
  exam: Exam;

  answers = new Map<number, Answer>();

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    public modalRef: MatDialogRef<AnwerExamModalComponent>
  ) { }

  ngOnInit(): void {
    this.course = this.data.course as Course;
    this.student = this.data.student as Student;
    this.exam = this.data.exam as Exam;
  }

  cancel() : void {
    this.modalRef.close();
  }

  answer(question: Question, event) : void {
    const text = event.target.value as string;
    const answer = new Answer();
    answer.student = this.student;
    answer.question = question;

    const exam = new Exam();
    exam.id = this.exam.id;
    exam.name = this.exam.name;

    answer.question.exam = exam;
    answer.text = text;

    this.answers.set(question.id, answer);
    console.log(this.answers);    
  }

}
