package example

import zio.stream.ZStream

import zio.console._
import zio.test._
import zio.test.environment.TestConsole
import zio.test.Assertion._
import zio.test.TestAspect._

import zio.ZIO
import zio.stream.ZTransducer
import zio.schema.DeriveSchema
import zio.schema.codec.JsonCodec
import zio.Chunk
import java.nio.file.Files
import java.nio.file.Paths

object BlockSpec extends DefaultRunnableSpec {
  private implicit val schemaJrpc = DeriveSchema.gen[JsonRpc[Block]]

  implicit val decoder: ZTransducer[Any, String, Byte, JsonRpc[Block]] =
    JsonCodec.decoder(schemaJrpc)

  def spec = suite("BlockSpec")(
    testM("sayHello correctly displays output") {
      for {
        jrpcOption <- ZStream
          .fromInputStream(getClass().getResourceAsStream("/block.json"))
          .transduce(decoder)
          .runHead

        jrpc <- ZIO.fromOption(jrpcOption)

      } yield assert(jrpc.jsonrpc)(equalTo("1.0"))
    }, //@@ failing,
    testM("sayHellcorrectly displays output") {
      for {
        jrpcOption <- ZStream
          .fromChunk(
            Chunk.fromArray(
              getClass().getResourceAsStream("/block.json").readAllBytes()
            )
          )
          .transduce(decoder)
          .runHead

      } yield assert("2.0")(equalTo("2.0"))
    }
  )
}
