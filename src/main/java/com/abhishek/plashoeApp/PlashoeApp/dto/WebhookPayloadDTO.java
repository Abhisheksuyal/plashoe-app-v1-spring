package com.abhishek.plashoeApp.PlashoeApp.dto;

import lombok.Data;

public class WebhookPayloadDTO {
    private String event;
    private Data data;

    public void setEvent(String event){
        this.event=event;
    }

    public  String getEvent(){
        return this.event;
    }

    public void setData(Data data){
        this.data=data;
    }
    public Data getData(){
        return this.data;
    }

    public static class Data{
        private String id;
        private int amount;
        private String currency;
        private String status;

        public String getStatus(){
            return this.status;
        }

        public void setStatus(String status){
            this.status=status;
        }

        public String getId(){
            return this.id;
        }
        public void setId(String id){
            this.id = id;
        }
        public void setAmount(int amount){
            this.amount=amount;
        }

        public int getAmount(){
            return this.amount;
        }
        public void setCurrency(String currency){
            this.currency=currency;
        }

        public String getCurrency(){
            return this.currency;
        }




    }
}
