package example

final case class JsonRpc[A](jsonrpc: String, id: Int, result: A)
