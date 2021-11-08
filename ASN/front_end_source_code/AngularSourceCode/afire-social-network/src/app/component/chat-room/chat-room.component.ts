import { Component, OnDestroy, OnInit } from '@angular/core';
import { LoginService } from 'src/app/service/login.service';
import { BroadcastObjectService } from 'src/app/shared/broadcast-object.service';
import { SOCKET_URL } from 'src/app/shared/global-variables';

@Component({
  selector: 'app-chat-room',
  templateUrl: './chat-room.component.html',
  styleUrls: ['./chat-room.component.css']
})
export class ChatRoomComponent implements OnInit, OnDestroy {

  ws: WebSocket | undefined;

  user: any
  message: any
  connected: any
  messages: any = []
  currentTime!: Date;

  constructor(
    private broadcastObjService: BroadcastObjectService,
    private loginService: LoginService
  ) {

    this.ws = new WebSocket(SOCKET_URL)

    this.ws.onopen = (event) => {
      let payload = {
        "message": "connected",
        "action": "connect",
        "user": this.user,
        "time": this.parseTime(new Date())
      }
      this.sendMessage(payload)
    }

    this.ws.onmessage = (event) => {
      console.log(event.data)
      let response = JSON.parse(event.data)

      if (response.action === 'update') {
        this.connected = response.connected
      }
      else if (response.action === 'connect') {
        this.messages.push(response)
      }
      else if (response.action === 'disconnect') {
        this.messages.push(response)
      }
      else if (response.action === 'send') {
        this.messages.push(response)
      }
    }
  }

  isSameUser(user: any): Boolean{
    return user.asnUserId === this.user.asnUserId ? true : false    
  }

  parseTime(d: any) {
    d = Number(d.valueOf(d));
    var tp = new Date(d);

    var min = String(tp.getMinutes()).padStart(2, "0");
    return tp.getHours() + ":" + min ;
  }


  ngOnDestroy(): void {
    let payload = {
      "action": "disconnect",
      "message": "disconnect",
      "user": this.user,
      "time": this.parseTime(new Date())
    }
    this.sendMessage(payload)
    this.ws?.close()
  }

  ngOnInit(): void {
    this.broadcastObjService.currentUser.subscribe(user => {
      this.user = user
    })
  }

  isLoggedIn(): Boolean {
    return this.loginService.getIsUserLoggedIn();
  }

  preparePayload() {
    let payload = {
      "action": "send",
      "user": this.user,
      "message": this.message,
      "time": this.parseTime(new Date())
    }
    this.sendMessage(payload)
  }

  sendMessage(payload: any) {
    this.message = ""
    this.ws?.send(JSON.stringify(payload))
  }

}
