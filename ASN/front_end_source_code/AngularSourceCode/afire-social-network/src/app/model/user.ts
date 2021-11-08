export interface IUser{
    asnUserId?: number;
    asnUser: string;
    asnUserEmail?: string;
    asnUserFirstname?: string;
    asnUserLastname?: string;
    asnUserProfileImageUrl?: string;
    asnUserPassword: string;
    asnProfileHobby?: string;
    asnProfileSport?: string;
    asnProfileFood?: string;
    asnProfileDescript?: string;
}

export class CUser{
    constructor(
       public asnUser: string,
       public asnUserEmail: string,
       public asnUserFirstname: string,
       public asnUserLastname: string,
       public asnUserProfileImageUrl: string,
       public asnUserPassword: string
    ){}
}