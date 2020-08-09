import {TaskStatus} from "./TaskStatus";

export class Task {

    id? : number
    description? : string
    taskStatus?: TaskStatus
    createdAt? : Date
    updatedAt? : Date
    isEditable?: boolean = false;

}
