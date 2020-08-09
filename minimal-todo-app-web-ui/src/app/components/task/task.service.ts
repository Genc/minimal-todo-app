import {Injectable} from '@angular/core';
import {ApiService} from "../../services/api.service";
import {Task} from './task';
import {Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class TaskService {

    private readonly TASK_PATH: string = "/tasks";

    constructor(private apiService: ApiService) {
    }

    getAllTasks(): Observable<Task[]> {
        return this.apiService.get(this.TASK_PATH);
    }

    createTask(task: Task): Observable<Task> {
        return this.apiService.post(this.TASK_PATH, task);
    }

    updateTask(taskId: number, task: Task): Observable<Task> {
        return this.apiService.put(this.TASK_PATH + '/' + taskId, task);
    }

    getTaskDetailById(taskId: number): Observable<Task> {
        return this.apiService.get(this.TASK_PATH + '/' + taskId);
    }

    deleteTask(taskId: number) {
        return this.apiService.delete(this.TASK_PATH + '/' + taskId);
    }

}
