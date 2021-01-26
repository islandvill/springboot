package com.islandvill.msa.service;

import com.islandvill.msa.domain.Multiplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class MultiplicationServiceTest {

    @MockBean
    private RandomGeneratorService RandomGeneratorService;

    @Autowired
    private MultiplicationService multiplicationService;

    @Test
    public void createRandoMultiplicationTest() {
        // given
        given(RandomGeneratorService.generateRandomFactor()).willReturn(50, 30);

        // when
        Multiplication multiplication = multiplicationService.createRandomMultiplication();

        // assert
        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
        assertThat(multiplication.getResult()).isEqualTo(1500);

    }
}