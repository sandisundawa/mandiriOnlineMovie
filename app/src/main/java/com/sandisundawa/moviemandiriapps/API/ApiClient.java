package com.sandisundawa.moviemandiriapps.API;

import android.os.Build;
import android.util.Log;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.CertificatePinner;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String base_url = "https://api.themoviedb.org/3/";
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient() {

        String hostname = "debug";
        okhttp3.CertificatePinner certificatePinner = new CertificatePinner.Builder()
                .add(hostname, "sha1/mBN/TTGneHe2Hq0yFG+SRt5nMZQ=")
                .add(hostname, "sha1/6CgvsAgBlX3PYiYRGedC0NZw7ys=")
                .build();


        okhttp3.ConnectionSpec spec = new okhttp3.ConnectionSpec.Builder(okhttp3.ConnectionSpec.MODERN_TLS)
                .tlsVersions(okhttp3.TlsVersion.TLS_1_2, okhttp3.TlsVersion.SSL_3_0, okhttp3.TlsVersion.TLS_1_0, okhttp3.TlsVersion.TLS_1_1)
                .cipherSuites(
                        okhttp3.CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
                        okhttp3.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        okhttp3.CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        okhttp3.CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
                        okhttp3.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,
                        okhttp3.CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
                        okhttp3.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
                        okhttp3.CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
                        okhttp3.CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA,
                        okhttp3.CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA,
                        okhttp3.CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA,
                        okhttp3.CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA, // for android 4..
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 //for android 4.4.2
                )
                .build();

//        disableCertificateValidation();


        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.
                    Builder().connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS).addInterceptor(new Interceptor())
                    .certificatePinner(certificatePinner)
                    .connectionSpecs(Arrays.asList(spec, okhttp3.ConnectionSpec.CLEARTEXT));

            //handshake handle
            enableTls12OnPreLollipop(builder);
//            UnsafeOkhttpClient.getUnsafeOkHttpClient();

            OkHttpClient okHttpClient = builder.build();

            retrofit = new Retrofit.Builder().baseUrl(base_url).
                    addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        return retrofit;


    }

    public static OkHttpClient.Builder enableTls12OnPreLollipop(OkHttpClient.Builder client) {
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 22) {
            try {
                SSLContext sc = SSLContext.getInstance("TLSv1.2");
                sc.init(null, null, null);
                client.sslSocketFactory(new Tls12SocketFactory(sc.getSocketFactory()));

                okhttp3.ConnectionSpec cs = new okhttp3.ConnectionSpec.Builder(okhttp3.ConnectionSpec.MODERN_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2).build();

                List<okhttp3.ConnectionSpec> specs = new ArrayList<>();
                specs.add(cs);
                specs.add(okhttp3.ConnectionSpec.COMPATIBLE_TLS);
                specs.add(ConnectionSpec.CLEARTEXT);

                client.connectionSpecs(specs);
            } catch (Exception exc) {
                Log.e("OkHttpTLSCompat", "Error while setting TLS 1.2", exc);
            }
        }

        return client;
    }
}
