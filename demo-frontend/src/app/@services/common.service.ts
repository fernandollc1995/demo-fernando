import { Injectable } from '@angular/core';
import { INotification } from '@models/notification.model';

@Injectable({
    providedIn: 'root'
})
export class CommonService {

    notification: INotification = {};
    constructor() {}
}