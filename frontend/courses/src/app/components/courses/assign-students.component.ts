import { Component, OnInit, ViewChild } from '@angular/core';
import { Course } from 'src/app/models/course';
import { ActivatedRoute } from '@angular/router';
import { CourseService } from 'src/app/services/course.service';
import { StudentService } from 'src/app/services/student.service';
import { Student } from 'src/app/models/student';
import { SelectionModel } from '@angular/cdk/collections';
import Swal from 'sweetalert2';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-assign-students',
  templateUrl: './assign-students.component.html',
  styleUrls: ['./assign-students.component.css']
})
export class AssignStudentsComponent implements OnInit {

  course: Course;

  studentsAssign: Student[] = [];
  students: Student[] = [];

  dataSource: MatTableDataSource<Student>;

  @ViewChild(MatPaginator, { static: true }) paginator : MatPaginator;

  showColumns: string[] = ['name', 'lastName', 'select']; // add all matColumnDef
  showColumnsStudents: string[] = ['id', 'name', 'lastName', 'email', 'eliminar'];

  selection: SelectionModel<Student> = new SelectionModel<Student>(true, []);

  pageSizeOptions = [5, 10,20,50];

  tabIndex = 0;

  constructor(
    private route: ActivatedRoute,
    private courseService: CourseService,
    private studentService: StudentService
  ) {

  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = +params.get('id');
      this.courseService.findById(id).subscribe(c => {
        this.course = c;
        this.students = this.course.students;
        this.initPaginator();        
      });
    })
  }

  private initPaginator() : void {
    this.dataSource = new MatTableDataSource<Student>(this.students);
    this.dataSource.paginator = this.paginator;
    this.paginator._intl.itemsPerPageLabel = 'Registros por página';
  }

  filters(name: string): void {
    if (name && name.length > 0) {
      this.studentService.filterByName(name.trim())
        .subscribe(students => this.studentsAssign = students.filter(s => {
          let filter = true;
          this.students.forEach(cs => {
            if (s.id === cs.id) {
              filter = false;
            }
          });
          return filter;
        }));
    }
  }

  masterToggle(): void {
    this.isAllSelected()
      ? this.selection.clear()
      : this.studentsAssign.forEach(s => this.selection.select(s));
  }

  isAllSelected(): boolean {
    const numSelected = this.selection.selected.length;
    const numRows = this.studentsAssign.length;
    return numSelected === numRows;
  }

  assignStudents(): void {
    console.log(this.selection.selected);
    this.courseService.assignStudents(this.course, this.selection.selected)
      .subscribe(c => {
        this.tabIndex = 2;
        Swal.fire('Asignados', `Alumnos asignados con éxito al curso ${this.course.name}`,
          'success');
        this.students = this.students.concat(this.selection.selected);
        this.initPaginator();
        this.studentsAssign = [];
        this.selection.clear();
      },
        e => {
          if (e.status === 500) {
            const message = e.error.message as string;
            if (message.indexOf('ConstraintViolationException') > -1) {
              Swal.fire('Cuidado:',
                'No se puede asignar al alumno, ya está asignado a otro curso.',
                'error');
            }
          }
        });
  }

  deleteStudent(student: Student): void {

    Swal.fire({
      title: 'Advertencia',
      text: `¿Está seguro que desea eliminar a ${student.name}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!'
    }).then((result) => {
      if (result.value) {

        this.courseService.deleteStudent(this.course, student)
          .subscribe(course => {
            this.students = this.students.filter(s => s.id !== student.id);
            this.initPaginator();
            Swal.fire('Eliminado:',
              `Alumno ${student.name} eliminado con éxito del curso ${course.name}`,
              'success');
          });
      }
    });

  }

}
