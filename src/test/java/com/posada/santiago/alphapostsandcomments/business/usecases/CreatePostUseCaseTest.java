package com.posada.santiago.alphapostsandcomments.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.posada.santiago.alphapostsandcomments.business.gateways.DomainEventRepository;
import com.posada.santiago.alphapostsandcomments.business.gateways.EventBus;
import com.posada.santiago.alphapostsandcomments.domain.commands.AddCommentCommand;
import com.posada.santiago.alphapostsandcomments.domain.commands.CreatePostCommand;
import com.posada.santiago.alphapostsandcomments.domain.events.CommentAdded;
import com.posada.santiago.alphapostsandcomments.domain.events.PostCreated;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CreatePostUseCaseTest {
    @Mock
    private EventBus eventBus;

    @Mock
    private DomainEventRepository eventRepository;

    @InjectMocks

    private CreatePostUseCase useCase;

    @Test
    @DisplayName("CreatePostUseCase")
    void apply_createPostUseCase(){
        CreatePostCommand command = new CreatePostCommand(
                "25456",
                //UUID.randomUUID().toString(),
                "Post author",
                "Title post"
        );
        PostCreated event = new PostCreated(
                command.getAuthor(),
                command.getTitle()
        );
        BDDMockito.when(this.eventRepository.saveEvent(ArgumentMatchers.any(DomainEvent.class)))
                .thenReturn(Mono.just(
                new PostCreated(
                        //     command.getPostId(),
                        //event.setAggregateRootId(command.getPostId()),
                        "Post author",
                        "Title post"
                )

                        ));
        //Act
        Mono<List<DomainEvent>> triggeredEvents = this.useCase.apply(Mono.just(command)).collectList();

        //Asert

        StepVerifier.create(triggeredEvents).expectSubscription().expectNextMatches(
                events -> events.size() == 1 && events.get(0) instanceof PostCreated
        ).verifyComplete();

        BDDMockito.verify(this.eventBus, BDDMockito.times(1))
                .publish(ArgumentMatchers.any(DomainEvent.class)); //con ArgumentMatcher mockeo el publish
        BDDMockito.verify(this.eventRepository, BDDMockito.times(1))
                .saveEvent(ArgumentMatchers.any(DomainEvent.class));

    }


}