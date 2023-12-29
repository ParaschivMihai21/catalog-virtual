import {Component} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {Router} from "@angular/router";

@Component({
    selector: 'app-register-modal',
    templateUrl: './register-modal.component.html',
    styleUrls: ['./register-modal.component.css']
})
export class RegisterModalComponent {
    titleModule: any;
    content: any;

    constructor(public activeModal: NgbActiveModal, private route: Router) {
    }


    public onCancel(): void {
        this.route.navigate(['login']);
        this.activeModal.close();
    }
}
