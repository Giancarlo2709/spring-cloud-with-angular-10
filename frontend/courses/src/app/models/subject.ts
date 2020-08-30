export class Subject {
    id: number;
    name: string;
    parent: Subject;
    childs: Subject[] = [];
}
