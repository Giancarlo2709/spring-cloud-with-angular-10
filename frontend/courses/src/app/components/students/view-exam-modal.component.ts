import { Component, OnInit, Inject } from '@angular/core';
import { Course } from 'src/app/models/course';
import { Exam } from 'src/app/models/exam';
import { Answer } from 'src/app/models/answer';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-view-exam-modal',
  templateUrl: './view-exam-modal.component.html',
  styleUrls: ['./view-exam-modal.component.css']
})
export class ViewExamModalComponent implements OnInit {

  course: Course;
  exam: Exam;
  answers: Answer[];

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    public modalRef: MatDialogRef<ViewExamModalComponent>
  ) { }

  ngOnInit(): void {
    this.course = this.data.course as Course;
    this.exam = this.data.exam as Exam;
    this.answers = this.data.answers as Answer[];
  }

  cancel() {
    this.modalRef.close();
  }

}
