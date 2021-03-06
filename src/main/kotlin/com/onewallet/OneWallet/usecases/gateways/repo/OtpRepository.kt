package com.onewallet.OneWallet.usecases.gateways.repo

import com.onewallet.OneWallet.usecases.user.RegisterUserByPhoneNumberUseCase
import com.onewallet.OneWallet.usecases.user.VerifyUserUseCase

interface OtpRepository: RegisterUserByPhoneNumberUseCase.OtpRepository, VerifyUserUseCase.OtpRepository