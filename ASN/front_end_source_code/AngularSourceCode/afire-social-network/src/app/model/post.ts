import { IUser } from "./user";

export interface IPost{    
    asnPostId?: number;
    asnPostDescription: string;
    asnPostImageUrl?: string;
    asnUserFK: IUser;    
    asnCreatedAt?: number;
}