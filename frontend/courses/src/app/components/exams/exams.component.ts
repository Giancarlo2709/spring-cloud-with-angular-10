import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonListComponent } from '../common-list.component';
import { Exam } from 'src/app/models/exam';
import { ExamService } from '../../services/exam.service';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-exams',
  templateUrl: './exams.component.html',
  styleUrls: ['./exams.component.css']
})
export class ExamsComponent 
  extends CommonListComponent<Exam, ExamService>
  implements OnInit {

    @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(service: ExamService) { 
    super(service);
    this.title = 'Listado de Exámenes';
    this.nameModel = 'Examen';
  }

  

}
