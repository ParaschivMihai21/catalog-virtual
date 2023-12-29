import {Component, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-enrolled-students',
  templateUrl: './enrolled-students.component.html',
  styleUrls: ['./enrolled-students.component.css']
})
export class EnrolledStudentsComponent implements OnInit {
  titleModule: string | undefined;
  content: any;
constructor(private activeModal:NgbActiveModal) {
}

  ngOnInit(): void {
  }
  public onCancel(): void {
    this.activeModal.close()
  }
}
