/**
 * Created by Qiang on 03/12/2016.
 */

import {Component, Optional} from "@angular/core";
import {MdDialogRef} from "@angular/material";
@Component({
    selector: 'confirm-dialog',
    templateUrl: 'confirm-dialog.component.html',
    styleUrls: ['confirm-dialog.component.css']
})
export class ConfirmDialog {

    messageTitle: string;
    messageContent: string;

    constructor(
        @Optional() public dialogRef: MdDialogRef<ConfirmDialog>
    ) { }

    confirm(): void {
        this.dialogRef.close("confirmed");
    }

    cancel(): void {
        this.dialogRef.close("cnacelled");
    }
}