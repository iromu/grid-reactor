package es.blew.grid.reactor.config;

import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.Encoder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.codec.EncoderHttpMessageWriter;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

public class CorsHttpMessageWriter<T> extends EncoderHttpMessageWriter<T> {

    private static final String ANY_ORIGIN = "*";
    private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";

    /**
     * Create a {@code CodecHttpMessageConverter} with the given {@link Encoder}.
     *
     * @param encoder the encoder to use
     */
    public CorsHttpMessageWriter(Encoder<T> encoder) {
        super(encoder);
    }


    @Override
    public boolean canWrite(ResolvableType type, MediaType mediaType) {
        return true;
    }

    @Override
    public List<MediaType> getWritableMediaTypes() {
        return Arrays.asList(MediaType.ALL);
    }

    @Override
    public Mono<Void> write(Publisher<? extends T> inputStream, ResolvableType type,
                            MediaType contentType, ReactiveHttpOutputMessage outputMessage) {
        HttpHeaders headers = outputMessage.getHeaders();
        headers.set(ACCESS_CONTROL_ALLOW_ORIGIN, ANY_ORIGIN);
        return super.write(inputStream, type, contentType, outputMessage);
    }
}
