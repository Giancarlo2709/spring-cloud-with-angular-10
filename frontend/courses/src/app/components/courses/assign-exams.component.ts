import { Component, OnInit, ViewChild } from '@angular/core';
import { Course } from 'src/app/models/course';
import { ActivatedRoute, Router } from '@angular/router';
import { ExamService } from 'src/app/services/exam.service';
import { CourseService } from 'src/app/services/course.service';
import { FormControl } from '@angular/forms';
import { Exam } from 'src/app/models/exam';

import { map, flatMap } from 'rxjs/operators';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import Swal from 'sweetalert2';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-assign-exams',
  templateUrl: './assign-exams.component.html',
  styleUrls: ['./assign-exams.component.css']
})
export class AssignExamsComponent implements OnInit {

  course: Course;
  autocompleteControl = new FormControl();
  examsFilters: Exam[] = [];

  examsAssign: Exam[] = [];

  exams: Exam[] = [];
  tabIndex = 0;

  showColumns: string[] = ['name', 'subject', 'delete'];
  showColumnsExams: string[] = ['id', 'name', 'subjects', 'delete'];

  pageSizeOptions = [5, 10,20,50];

  dataSource: MatTableDataSource<Exam>;

  @ViewChild(MatPaginator, { static : true }) paginator : MatPaginator;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private courseService: CourseService,
    private examService: ExamService
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id: number = +params.get('id');
      this.courseService.findById(id).subscribe(c => {
        this.course = c;
        this.exams = this.course.exams;
        this.initPaginator();
      });
    });
    this.autocompleteControl.valueChanges.pipe(
      map(value => typeof value === 'string' ? value : value.name),
      flatMap(value => value ? this.examService.filterByName(value) : [])
    )
      .subscribe(exams => this.examsFilters = exams);
  }

  private initPaginator() : void {
    this.dataSource = new MatTableDataSource<Exam>(this.exams);
    this.dataSource.paginator = this.paginator;
    this.paginator._intl.itemsPerPageLabel = 'Registros por página';
  }

  showName(exam?: Exam): string {
    return exam ? exam.name : '';
  }

  selectedExam(event: MatAutocompleteSelectedEvent): void {
    const exam = event.option.value as Exam;

    if (!this.exists(exam.id)) {
      this.examsAssign = this.examsAssign.concat(exam);
      console.log(this.examsAssign);
    } else {
      Swal.fire('Error:',
        `El examen ${exam.name} ya está asignado al curso`,
        'error');
    }
    this.resetAutocomplete(event);
  }

  resetAutocomplete(event: MatAutocompleteSelectedEvent): void {
    this.autocompleteControl.setValue('');
    event.option.deselect();
    event.option.focus();
  }

  private exists(id: number): boolean {
    let exist = false;
    this.examsAssign.concat(this.exams)
      .forEach(e => {
        if (id === e.id) {
          exist = true;
        }
      });
    return exist;
  }

  deleteOfAssign(exam: Exam) {
    this.examsAssign = this.examsAssign.filter(
      e =>  exam.id !== e.id
    );
  }

  assign() {
    console.log(this.examsAssign);
    
    this.courseService.assignExams(this.course, this.examsAssign)
      .subscribe(c => {
        this.exams = this.exams.concat(this.examsAssign);
        this.initPaginator();
        this.examsAssign = [];      
        Swal.fire(
          'Asignados:',
          `Exámenes con éxito al curso ${c.name}`,
          'success'
        );
        this.tabIndex = 2;
      });
  }

  deleteExamOfCourse(exam: Exam) {
    Swal.fire({
      title: 'Advertencia',
      text: `¿Está seguro que desea eliminar a ${exam.name}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!'
    }).then((result) => {
      if (result.value) {

        this.courseService.deleteExam(this.course, exam)
          .subscribe(course => {
            this.exams = this.exams.filter(e => e.id !== exam.id);
            this.initPaginator();
            Swal.fire('Eliminado:',
              `Examen ${exam.name} eliminado con éxito del curso ${course.name}`,
              'success');
          });
      }
    });
  }

}
