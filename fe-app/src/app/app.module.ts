import { AppComponent } from './app.component';
import { ProjectService } from './shared/project/project.service';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ProjectListComponent } from './project-list/project-list.component';
import { MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatToolbarModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProjectEditComponent } from './project-edit/project-edit.component';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import {MatTableModule} from '@angular/material/table';
import {CdkTableModule} from '@angular/cdk/table';



const appRoutes: Routes = [
  { path: '', redirectTo: '/project-list', pathMatch: 'full' },
  {
    path: 'project-list',
    component: ProjectListComponent
  },
  {
    path: 'project-add',
    component: ProjectEditComponent
  },
  {
    path: 'project-edit/:id',
    component: ProjectEditComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    ProjectListComponent,
    ProjectEditComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    MatTableModule,
    CdkTableModule
  ],
  providers: [ProjectService],
  bootstrap: [AppComponent]
})
export class AppModule { }
