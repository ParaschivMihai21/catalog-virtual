import {Component, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
    selector: 'app-modal',
    templateUrl: './modal.component.html',
    styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit {
    titleModule: string | undefined;
    content: any;

    constructor(public activeModal: NgbActiveModal) {
    }

    ngOnInit(): void {
        if (!this.activeModal) {
            console.error('Active modal is undefined');
        }
    }

    public onCancel(): void {
        this.activeModal.close()
    }


}
