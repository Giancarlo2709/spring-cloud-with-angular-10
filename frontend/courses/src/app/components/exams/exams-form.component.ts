import { Component, OnInit } from '@angular/core';
import { CommonFormComponent } from '../common-form.component';
import { Exam } from 'src/app/models/exam';
import { ExamService } from 'src/app/services/exam.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'src/app/models/subject';
import { Question } from 'src/app/models/question';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-exams-form',
  templateUrl: './exams-form.component.html',
  styles: [
  ]
})
export class ExamsFormComponent
  extends CommonFormComponent<Exam, ExamService> implements OnInit {

  subjectsParent: Subject[] = [];
  subjectsChild: Subject[] = [];

  errorQuestions : string;

  constructor(
    service: ExamService,
    router: Router,
    route: ActivatedRoute) {
    super(service, router, route);
    this.title = 'Crear Examen';
    this.model = new Exam();
    this.redirect = '/exams';
    this.nameModel = 'Examen';
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id: number = +params.get('id');
      if (id) {
        this.service.findById(id).subscribe(m => {
          this.model = m;
          this.title = 'Editar ' + this.nameModel;
          this.loadChilds();
          /*this.service.findAllSubject().subscribe(subjects => {
            this.subjectsChild = subjects
            .filter(s => s.parent && s.parent.id === this.model.subjectParent.id)
          });*/
        });
      }
    });

    this.service.findAllSubject().subscribe(subjects => {
      this.subjectsParent = subjects.filter(a => !a.parent);    
    });
  }

  public create() : void {
    this.deleteQuestionsEmpty();
    if (this.model.questions.length === 0) {
      this.errorQuestions = 'Examen debe tener preguntas';
      // Swal.fire('Error Preguntas', 'Examen debe tener preguntas', 'error');
      return;
    }
    this.errorQuestions = undefined; 
    super.create();
  }

  public update() : void {
    this.deleteQuestionsEmpty();
    if (this.model.questions.length === 0) {
      this.errorQuestions = 'Examen debe tener preguntas';
      // Swal.fire('Error Preguntas', 'Examen debe tener preguntas', 'error');
      return;
    }
    this.errorQuestions = undefined;
    super.update();
  }

  public loadChilds() : void {
    this.subjectsChild = this.model.subjectParent 
    ? this.model.subjectParent.childs
    : [];
  }

  compareSubject(s1: Subject, s2: Subject) : boolean {
    if (s1 === undefined && s2 === undefined) {
      return true;
    }

    return (s1 === null || s2 === null || s1 === undefined || s2 === undefined)
      ? false
      : s1.id === s2.id;
  }

  addQuestion() : void {
    this.model.questions.push(new Question());
  }

  assignText(question: Question, event: any) : void {
    question.text = event.target.value;
    console.log(this.model);
  }

  deleteQuestion(question: Question) : void {
    this.model.questions = this.model.questions.filter(q => q.text !== question.text);
  }

  deleteQuestionsEmpty() : void {
    this.model.questions = this.model.questions.filter(p => p.text && p.text.length > 0);
  }

}
