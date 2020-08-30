import { Student } from "./student";
import { Question } from "./question";

export class Answer {
    id: string;
    text: string;
    student: Student;
    question: Question;
}
