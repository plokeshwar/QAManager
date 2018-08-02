import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs/Subscription';
import {ActivatedRoute, Router} from '@angular/router';
import {ProjectService} from '../shared/project/project.service';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-project-edit',
  templateUrl: './project-edit.component.html',
  styleUrls: ['./project-edit.component.css']
})
export class ProjectEditComponent implements OnInit, OnDestroy {
  project: any = {};

  sub: Subscription;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private projectService: ProjectService) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.projectService.get(id).subscribe((project: any) => {
          if (project) {
            this.project = project;
            this.project.id = project.id;
          } else {
            console.log(`Project with id '${id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/project-list']);
  }


  save(form: NgForm) {
    this.projectService.save(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  update(id, form: NgForm) {
    if (id == null) {
      this.save(form);
    } else {
      this.projectService.update(id, form).subscribe(result => {
        this.gotoList();
      }, error => console.error(error));
    }
  }

  remove(id) {
    this.projectService.remove(id).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }
}