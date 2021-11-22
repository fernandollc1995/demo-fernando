import { INotification } from "@models/notification.model";

export function ntfOk(message: string): INotification {
    return { type: 'success', message };
}

export function ntfError(message: string): INotification {
    return { type: 'warning', message };
}