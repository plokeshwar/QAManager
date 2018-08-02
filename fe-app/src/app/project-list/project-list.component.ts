import { Component, OnInit } from '@angular/core';

import { ProjectService } from '../shared/project/project.service';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})




export class ProjectListComponent implements OnInit {
  displayedColumns: string[] = ['position', 'name', 'description'];
  projects: Array<any>;

  constructor(private projectService: ProjectService) { }

  ngOnInit() {
    this.projectService.getAll().subscribe(data => {
      this.projects = data;
    });
  }
  
}


