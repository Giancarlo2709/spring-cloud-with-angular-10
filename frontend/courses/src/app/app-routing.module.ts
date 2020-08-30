import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StudentsComponent } from './components/students/students.component';
import { CoursesComponent } from './components/courses/courses.component';
import { ExamsComponent } from './components/exams/exams.component';
import { StudentsFormComponent } from './components/students/students-form.component';
import { CoursesFormComponent } from './components/courses/courses-form.component';
import { ExamsFormComponent } from './components/exams/exams-form.component';
import { AssignStudentsComponent } from './components/courses/assign-students.component';
import { AssignExamsComponent } from './components/courses/assign-exams.component';
import { AnswerExamComponent } from './components/students/answer-exam.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'courses' },
  { path: 'students', component: StudentsComponent },
  { path: 'students/form', component: StudentsFormComponent },
  { path: 'students/form/:id', component: StudentsFormComponent },
  { path: 'students/answer-exam/:id', component: AnswerExamComponent },
  { path: 'courses', component: CoursesComponent },
  { path: 'courses/form', component: CoursesFormComponent },
  { path: 'courses/form/:id', component: CoursesFormComponent },
  { path: 'exams', component: ExamsComponent }, 
  { path: 'exams/form', component: ExamsFormComponent }, 
  { path: 'exams/form/:id', component: ExamsFormComponent },
  { path: 'courses/assign-students/:id', component: AssignStudentsComponent },
  { path: 'courses/assign-exams/:id', component: AssignExamsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
