import { Component, OnInit } from '@angular/core';
import { CommonService } from '@services/common.service';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {

  constructor(public commonService: CommonService) { }

  ngOnInit(): void {
    console.log(this.commonService.notification);
  }

  close(){
    this.commonService.notification = {}
  }

}
