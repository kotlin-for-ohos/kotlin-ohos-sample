/*
 * Copyright (c) 2021-2023 Huawei Device Co., Ltd.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * Provides interfaces to generate system logs.
 *
 * @namespace hilog
 * @syscap SystemCapability.HiviewDFX.HiLog
 * @since 7
 */
/**
 * Provides interfaces to generate system logs.
 *
 * @namespace hilog
 * @syscap SystemCapability.HiviewDFX.HiLog
 * @crossplatform
 * @since 10
 */
@JsModule("@ohos.hilog")
external class HiLog {

    companion object {

        /**
         * Outputs info-level logs.
         *
         * @param { number } domain Indicates the service domain, which is a hexadecimal integer ranging from 0x0 to 0xFFFF
         * @param { string } tag Identifies the log tag, length cannot exceed 32 bytes.
         * @param { string } format Indicates the log format string.
         * @param { any[] }args Indicates the log parameters.
         * @syscap SystemCapability.HiviewDFX.HiLog
         * @since 7
         */
        /**
         * Outputs info-level logs.
         *
         * @param { number } domain Indicates the service domain, which is a hexadecimal integer ranging from 0x0 to 0xFFFF
         * @param { string } tag Identifies the log tag, length cannot exceed 32 bytes.
         * @param { string } format Indicates the log format string.
         * @param { any[] }args Indicates the log parameters.
         * @syscap SystemCapability.HiviewDFX.HiLog
         * @crossplatform
         * @since 10
         */
        fun info(domain: Int, tag: String, format: String, vararg args: Any)
    }

}
