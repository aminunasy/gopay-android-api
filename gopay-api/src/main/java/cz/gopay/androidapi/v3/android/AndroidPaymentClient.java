package cz.gopay.androidapi.v3.android;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;

import java.io.IOException;


import cz.gopay.androidapi.v3.GsonAbstractImpl;
import cz.gopay.androidapi.v3.PaymentClient;
import cz.gopay.androidapi.v3.exception.WebClientException;
import cz.gopay.androidapi.v3.model.access.AuthHeader;
import cz.gopay.androidapi.v3.model.payment.BasePayment;
import cz.gopay.androidapi.v3.model.payment.NextPayment;
import cz.gopay.androidapi.v3.model.payment.Payment;
import cz.gopay.androidapi.v3.model.payment.PaymentResult;

/**
 * Created by Frantisek Sichinger on 23.2.16.
 */
public class AndroidPaymentClient extends GsonAbstractImpl implements PaymentClient {

    protected AndroidPaymentClient(String apiUrl) {
        super(apiUrl);

    }

    @Override
    public Payment createPayment(AuthHeader authHeader, BasePayment createPayment) {
        Response response = null;

        try {
            response = Request.Post(apiUrl + "/payments/payment").
                    addHeader(ACCEPT, ContentType.APPLICATION_JSON.toString())
                    .addHeader(CONTENT_TYPE, ContentType.APPLICATION_JSON.toString()).
                            addHeader(AUTHORIZATION, authHeader.
                                    getAuhorization())
                    .bodyString(marshall(createPayment), ContentType.APPLICATION_JSON)
                    .execute();
        } catch (IOException ex) {
            throw new WebClientException(ex);
        }

        return unMarshall(response, Payment.class);
    }

    @Override
    public PaymentResult refundPayment(AuthHeader authHeader, Long id, Long amount) {
        Response response = null;

        try {
            response = Request.
                    Post(apiUrl + "/payments/payment/" + id + "/refund")
                    .addHeader(ACCEPT, ContentType.APPLICATION_JSON.toString())
                    .addHeader(CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.toString())
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .bodyString("amount=" + amount, ContentType.APPLICATION_JSON)
                    .execute();
        } catch (IOException ex) {
            throw new WebClientException(ex);
        }
        return unMarshall(response, PaymentResult.class);
    }

    @Override
    public Payment createRecurrentPayment(AuthHeader authHeader, Long id, NextPayment createPayment) {
        Response response = null;

        try {
            response = Request.
                    Post(apiUrl + "/payments/payment/" + id + "/create-recurrence")
                    .addHeader(ACCEPT, ContentType.APPLICATION_JSON.toString()).
                            addHeader(CONTENT_TYPE, ContentType.APPLICATION_JSON.toString())
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .bodyString(marshall(createPayment), ContentType.TEXT_XML).
                            execute();
        } catch (IOException ex) {
            throw new WebClientException(ex);
        }

        return unMarshall(response, Payment.class);
    }

    @Override
    public PaymentResult voidRecurrence(AuthHeader authHeader, Long id) {
        Response response = null;

        try {
            response = Request.
                    Post(apiUrl + "/payments/payment/" + id + "/void-recurrence")
                    .addHeader(ACCEPT, ContentType.APPLICATION_JSON.toString())
                    .addHeader(CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.toString())
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization()).
                            execute();
        } catch (IOException ex) {
            throw new WebClientException(ex);
        }

        return unMarshall(response, PaymentResult.class);
    }

    @Override
    public PaymentResult capturePayment(AuthHeader authHeader, Long id) {
        Response response = null;

        try {
            response = Request.
                    Post(apiUrl + "/payments/payment/" + id + "/capture")
                    .addHeader(ACCEPT, ContentType.APPLICATION_JSON.toString())
                    .addHeader(CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.toString())
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization()).
                            execute();
        } catch (IOException ex) {
            throw new WebClientException(ex);
        }

        return unMarshall(response, PaymentResult.class);
    }

    @Override
    public PaymentResult voidAuthorization(AuthHeader authHeader, Long id) {
        Response response = null;

        try {
            response = Request.
                    Post(apiUrl + "/payments/payment/" + id + "/void-authorization")
                    .addHeader(ACCEPT, ContentType.APPLICATION_JSON.toString())
                    .addHeader(CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.toString())
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization()).
                            execute();
        } catch (IOException ex) {
            throw new WebClientException(ex);
        }

        return unMarshall(response, PaymentResult.class);

    }

    @Override
    public Payment getPayment(AuthHeader authHeader, Long id) {
        Response response = null;

        try {
            response = Request.Get(apiUrl + "/payments/payment/" + id).
                    addHeader(ACCEPT, ContentType.APPLICATION_JSON.toString())
                    .addHeader(CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.toString())
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization()).
                            execute();
        } catch (IOException ex) {
            throw new WebClientException(ex);
        }

        return unMarshall(response, Payment.class);
    }

}

