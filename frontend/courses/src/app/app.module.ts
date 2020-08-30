import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StudentsComponent } from './components/students/students.component';
import { CoursesComponent } from './components/courses/courses.component';
import { ExamsComponent } from './components/exams/exams.component';
import { LayoutModule } from './layout/layout.module';
import { StudentsFormComponent } from './components/students/students-form.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatPaginatorModule } from '@angular/material/paginator';
import { CoursesFormComponent } from './components/courses/courses-form.component';
import { ExamsFormComponent } from './components/exams/exams-form.component';

import { MatTableModule } from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatButtonModule} from '@angular/material/button';
import { AssignStudentsComponent } from './components/courses/assign-students.component';
import {MatCardModule} from '@angular/material/card';
import {MatTabsModule} from '@angular/material/tabs';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { AssignExamsComponent } from './components/courses/assign-exams.component';
import { AnswerExamComponent } from './components/students/answer-exam.component';
import { AnwerExamModalComponent } from './components/students/anwer-exam-modal.component';
import {MatDialogModule} from '@angular/material/dialog';
import {MatExpansionModule} from '@angular/material/expansion';
import { ViewExamModalComponent } from './components/students/view-exam-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    StudentsComponent,
    CoursesComponent,
    ExamsComponent,
    StudentsFormComponent,
    CoursesFormComponent,
    ExamsFormComponent,
    AssignStudentsComponent,
    AssignExamsComponent,
    AnswerExamComponent,
    AnwerExamModalComponent,
    ViewExamModalComponent
  ],
  // Solo para los modales
  entryComponents: [
    AnwerExamModalComponent,
    ViewExamModalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LayoutModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatPaginatorModule,
    MatTableModule,
    MatInputModule,
    MatCheckboxModule,
    MatButtonModule,
    MatCardModule,
    MatTabsModule,
    MatAutocompleteModule,
    MatDialogModule,
    MatExpansionModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
