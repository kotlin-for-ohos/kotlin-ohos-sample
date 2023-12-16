import { HelloJs } from '../../js/hello-kotlinjs'
import hilog from '@ohos.hilog'

@Entry
@Component
struct Index {
  @State message: string = 'Hello World'

  build() {
    Row() {
      Column() {
        Text(this.message)
          .fontSize(50)
          .fontWeight(FontWeight.Bold)
          .onClick((e) => {
            new HelloJs().sayHello()
            hilog.info(10086, "kjs", "Hello from Kotlin js with hilog!!")
          })
      }
      .width('100%')
    }
    .height('100%')
  }
}