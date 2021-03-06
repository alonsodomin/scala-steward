/*
 * Copyright 2018 scala-steward contributors
 *
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

package eu.timepit.scalasteward.git

import cats.Eq
import cats.implicits._
import io.circe.Decoder

final case class Sha1(value: String)

object Sha1 {
  implicit val sha1Eq: Eq[Sha1] =
    Eq.by(_.value)

  implicit val sha1Decoder: Decoder[Sha1] =
    Decoder[String].map(Sha1.apply)
}
