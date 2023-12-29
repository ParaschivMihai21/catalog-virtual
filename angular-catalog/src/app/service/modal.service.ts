import {Injectable} from '@angular/core';
import {NgbModal, NgbModalOptions} from "@ng-bootstrap/ng-bootstrap";
import {ModalComponent} from "../modals/modal/modal.component";
import {EnrolledStudentsComponent} from "../modals/enrolled-students/enrolled-students.component";
import {AssignComponent} from "../assign/assign.component";
import {RegisterModalComponent} from "../modals/register-modal/register-modal.component";


@Injectable({
  providedIn: 'root'
})
export class ModalService {
  constructor(private modalService: NgbModal) {
  }

  openModal(title: string, content: any) {

    const modalRef = this.modalService.open(ModalComponent);
    modalRef.componentInstance.titleModule = title;
    modalRef.componentInstance.content = content;
    return modalRef.result;
  }

  openModalEnroll(title: string, content: any, size: 'sm' | 'lg' | 'xl') {
    const modalOptions: NgbModalOptions = {
      size: size
    };
    const modalRef = this.modalService.open(EnrolledStudentsComponent, modalOptions);
    modalRef.componentInstance.titleModule = title;
    modalRef.componentInstance.content = content;
    return modalRef.result;
  }
  openEnroll(title: string, content: any, size: 'sm' | 'lg' | 'xl') {
    const modalOptions: NgbModalOptions = {
      size: size
    };
    const modalRef = this.modalService.open(AssignComponent, modalOptions);
    modalRef.componentInstance.titleModule = title;
    modalRef.componentInstance.content = content;
    modalRef.componentInstance.id = content.studentId;
    modalRef.componentInstance.selectedCourseId = content.courseId;
    return modalRef.result;
  }
  openModalRegister(title: string, content: any) {

    const modalRef = this.modalService.open(RegisterModalComponent);
    modalRef.componentInstance.titleModule = title;
    modalRef.componentInstance.content = content;
    return modalRef.result;
  }

}
