import {Component, OnInit} from '@angular/core';
import {TaskService} from './task.service';
import {Task} from './task';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TaskStatus} from './TaskStatus';

@Component({
    selector: 'app-task',
    templateUrl: './task.component.html',
    styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

    task: Task;
    tasks: Task[] = [];

    todoForm: FormGroup;

    constructor(private formBuilder: FormBuilder, private taskService: TaskService) {
        this.createTodoForm();
    }

    createTodoForm() {
        this.todoForm = this.formBuilder.group({
            description: ['', Validators.required]
        });
    }

    ngOnInit(): void {

        this.taskService.getAllTasks().subscribe(data => {
            this.tasks = data;
        });

    }

    createTask() {

        if (this.todoForm.valid) {
            this.task = Object.assign({}, this.todoForm.value);
            this.taskService.createTask(this.task).subscribe(() => this.ngOnInit());
        }

        this.todoForm.reset();
    }

    updateTask(task: Task) {
        this.taskService.updateTask(task.id, task).subscribe(() => this.ngOnInit());
    }

    deleteTask(taskId: number) {
        this.taskService.deleteTask(taskId).subscribe(() => this.deleteTaskFromList(taskId));
    }

    deleteTaskFromList(taskId: number) {
        this.tasks.slice(taskId);
        this.ngOnInit();
    }

    isDone(taskStatus: TaskStatus) {
        return taskStatus === TaskStatus.DONE;
    }

    changedTaskStatus(target: any, task: Task) {

        if (target.checked === true) {
            task.taskStatus = TaskStatus.DONE;
        } else {
            task.taskStatus = TaskStatus.TODO;
        }

        this.taskService.updateTask(task.id, task).subscribe(resp => console.log(resp));
    }

    changedTaskDescription(task: Task) {
        this.taskService.updateTask(task.id, task).subscribe(resp => console.log(resp));
    }

    editTaskDescriptionEvent(task: Task) {
        task.isEditable = !task.isEditable;
    }

    isEmptyTaskList() {
        return this.tasks.length === 0;
    }

}
