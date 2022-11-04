package ru.sfedu.rickandmortyapi2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import org.junit.Rule

open class ViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockkRule = MockKRule(this)

}